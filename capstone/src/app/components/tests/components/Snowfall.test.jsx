import { render, screen, act } from '@testing-library/react';  
import Snowfall from './Snowfall';  

beforeAll(() => {
  window.requestAnimationFrame = (callback) => {
    setTimeout(callback, 0);
  };
});

test('renders the snowflakes container', () => {
  render(<Snowfall />);
  const snowflakesContainer = screen.getByClass('snowflakes');
  expect(snowflakesContainer).toBeInTheDocument(); 
});

test('creates 100 snowflake elements', () => {
  render(<Snowfall />);
  
  act(() => {});

  const snowflakes = screen.getAllByClassName('snowflake');
  expect(snowflakes).toHaveLength(100);  
});

test('snowflakes have correct style properties', () => {
  render(<Snowfall />);

  act(() => {});

  const snowflakes = screen.getAllByClassName('snowflake');

  snowflakes.forEach(snowflake => {
    const left = parseFloat(snowflake.style.left);
    expect(left).toBeGreaterThanOrEqual(0);
    expect(left).toBeLessThanOrEqual(100);
    
    const duration = parseFloat(snowflake.style.animationDuration);
    expect(duration).toBeGreaterThanOrEqual(2);
    expect(duration).toBeLessThanOrEqual(5);

    const delay = parseFloat(snowflake.style.animationDelay);
    expect(delay).toBeGreaterThanOrEqual(0);
    expect(delay).toBeLessThanOrEqual(5);
  });
});
