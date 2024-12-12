/* eslint-disable @next/next/no-img-element */
import Light from "./Light";

const ChristmasTree: React.FC = () => {
  const lights = [
    { color: "red", position: { x: "59%", y: "20%" } },
    { color: "green", position: { x: "40%", y: "25%" } },
    { color: "blue", position: { x: "55%", y: "30%" } },
    { color: "yellow", position: { x: "45%", y: "35%" } },
    { color: "maroon", position: { x: "65%", y: "40%" } },
    { color: "green", position: { x: "35%", y: "45%" } },
    { color: "red", position: { x: "50%", y: "50%" } },
    { color: "white", position: { x: "63%", y: "60%" } },
    { color: "blue", position: { x: "35%", y: "60%" } },
    { color: "pink", position: { x: "45%", y: "65%" } },
    { color: "maroon", position: { x: "55%", y: "70%" } },
    { color: "gold", position: { x: "70%", y: "75%" } },
    { color: "white", position: { x: "60%", y: "80%" } },
    { color: "red", position: { x: "30%", y: "75%" } },
    { color: "blue", position: { x: "70%", y: "52%" } },
  ];
  
  return (
    <div className="relative w-full h-full">
      <img
        src="/tree.png"
        alt="Christmas tree"
        className="w-full h-full object-contain"
      />

      {lights.map((light, index) => (
        <Light key={index} color={light.color} position={light.position} />
      ))}
    </div>
  );
};

export default ChristmasTree;
