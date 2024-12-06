interface LightProps {
  color: string;
  position: { x: number; y: number };
}

const Light: React.FC<LightProps> = ({ color, position }) => {
  return (
    <div
      className={`absolute rounded-full w-4 h-4 animate-blink`}
      style={{
        backgroundColor: color,
        left: `${position.x}px`,
        top: `${position.y}px`,
      }}
    ></div>
  );
};

export default Light;
