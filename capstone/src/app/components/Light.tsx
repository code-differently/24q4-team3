interface LightProps {
  color: string;
  position: { x: string; y: string };
}


const Light: React.FC<LightProps> = ({ color, position }) => {
  return (
    <div
      className="absolute w-4 h-4 rounded-full blink"
      style={{
        left: position.x,
        top: position.y,
        transform: "translate(-50%, -50%)",
        backgroundColor: color,
      }}
    />
  );
};

export default Light;


