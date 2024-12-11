import { render, screen } from '@testing-library/react';
import ChristmasTree from '../ChristmasTree';
import '@testing-library/jest-dom';

describe('ChristmasTree Component', () => {
  it('should render the Christmas tree with all lights correctly', () => {
    // Render the ChristmasTree component and get the render result
    const { container } = render(<ChristmasTree />);

    // Check if the tree image is rendered
    const treeImage = screen.getByAltText('Christmas tree');
    expect(treeImage).toBeInTheDocument();

    // Check if the correct number of lights are rendered (should be 15 lights)
    const lights = container.querySelectorAll('div');
    expect(lights).toHaveLength(16); // 1 image + 15 lights = 16 divs

    // Check that the first light has the correct position
    const firstLight = lights[1]; // The first light will be at index 1, as index 0 is the tree image
    expect(firstLight).toHaveStyle('left: 140px; top: 70px;');

    // You can check other lights similarly by accessing their position in the array
    const secondLight = lights[2];
    expect(secondLight).toHaveStyle('left: 80px; top: 100px;');
  });
});


