"use client";
import React, { useEffect } from "react";
import "./Snowfall.css"; 

const Snowfall = () => {
  useEffect(() => {
    const numSnowflakes = 100; 
    const snowflakesContainer = document.querySelector(".snowflakes");

    for (let i = 0; i < numSnowflakes; i++) {
      const snowflake = document.createElement("div");
      snowflake.classList.add("snowflake");
      snowflake.style.left = `${Math.random() * 100}vw`; 
      snowflake.style.animationDuration = `${Math.random() * 3 + 2}s`; 
      snowflake.style.animationDelay = `${Math.random() * 5}s`; 
      snowflakesContainer?.appendChild(snowflake);
    }

  }, []);

  return <div className="snowflakes"></div>;
};

export default Snowfall;
