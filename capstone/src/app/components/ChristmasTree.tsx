/* eslint-disable @next/next/no-img-element */
import Light from "./Light";

const ChristmasTree: React.FC = () => {
  const lights = [
    { color: "red", position: { x: 140, y: 70 } }, 
    { color: "green", position: { x: 80, y: 100 } },
    { color: "blue", position: { x: 125, y: 130 } }, 
    { color: "yellow", position: { x: 70, y: 200 } },
    { color: "maroon", position: { x: 100, y: 300 } },
    { color: "green", position: { x: 60, y: 250 } },
    { color: "gold", position: { x: 165, y: 118 } },
    { color: "white", position: { x: 120, y: 180 } },
    { color: "blue", position: { x: 50, y: 170 } },
    { color: "pink", position: { x: 140, y: 360 } },
    { color: "maroon", position: { x: 40, y: 350 } },
    { color: "gold", position: { x: 150, y: 260 } },
    { color: "white", position: { x: 180, y: 300 } },
    { color: "pink", position: { x: 190, y: 200 } },
    { color: "blue", position: { x: 110, y: 230 } }, 
 
  ];

  return (
    <div className="relative h-96 w-48 mx-auto">
      <img src="tree.png" alt="Christmas tree" className="absolute inset-0 w-full h-full" />
      {lights.map((light, index) => (
        <Light
          key={index}
          color={light.color}
          position={light.position}
        />
      ))}
    </div>
  );
};

export default ChristmasTree;

  