'use client';

import React, { useEffect, useState } from 'react';
import './Snowfall.css'; // Include the CSS file for animation

const Snowfall: React.FC = () => {
  const [snowflakes, setSnowflakes] = useState<number[]>([]);

  useEffect(() => {
    const flakeCount = 50; // Number of snowflakes
    setSnowflakes(Array.from({ length: flakeCount }, (_, i) => i));
  }, []);

  return (
    <div className="snowfall">
      {snowflakes.map((flake) => (
        <div
          key={flake}
          className="snowflake"
          style={{
            '--i': flake,
            left: `${Math.random() * 100}%`,
            animationDuration: `${Math.random() * 3 + 5}s`,
            animationDelay: `${Math.random() * 5}s`,
          } as React.CSSProperties}
        >
          ❄️
        </div>
      ))}
    </div>
  );
};

export default Snowfall;
