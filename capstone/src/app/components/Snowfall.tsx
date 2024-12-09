"use client";
import React, { useEffect } from "react";
import "./Snowfall.css"; // Importing the CSS for the snowfall animation

const Snowfall = () => {
  useEffect(() => {
    // Snowflake fall logic (optional, if you want to add JavaScript-based effects)
    const numSnowflakes = 100; // Number of snowflakes
    const snowflakesContainer = document.querySelector(".snowflakes");

    for (let i = 0; i < numSnowflakes; i++) {
      const snowflake = document.createElement("div");
      snowflake.classList.add("snowflake");
      snowflake.style.left = `${Math.random() * 100}vw`; // Random horizontal position
      snowflake.style.animationDuration = `${Math.random() * 3 + 2}s`; // Random animation duration
      snowflake.style.animationDelay = `${Math.random() * 5}s`; // Random animation delay
      snowflakesContainer?.appendChild(snowflake);
    }

  }, []);

  return <div className="snowflakes"></div>;
};

export default Snowfall;
