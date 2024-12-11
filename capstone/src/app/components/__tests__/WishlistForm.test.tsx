import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import WishlistForm from '../WishlistForm'; 
import '@testing-library/jest-dom';

// Mock localStorage 
const mockLocalStorage = (() => {
  let store: Record<string, string> = {};
  return {
    getItem: (key: string): string | null => store[key] || null,
    setItem: (key: string, value: string): void => {
      store[key] = value;
    },
    removeItem: (key: string): void => {
      delete store[key];
    },
    clear: (): void => {
      store = {};
    },
  };
})();
Object.defineProperty(window, 'localStorage', { value: mockLocalStorage });

describe('WishlistForm', () => {
  it('should render form elements correctly', () => {
    render(<WishlistForm />);

    expect(screen.getByText(/🎄 My Christmas Wishlist 🎄/i)).toBeInTheDocument();
    expect(screen.getByPlaceholderText('Enter product URL')).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /add to wishlist/i })).toBeInTheDocument();
  });

  it('should accept user input for the product URL', () => {
    render(<WishlistForm />);

    const input = screen.getByPlaceholderText('Enter product URL') as HTMLInputElement;
    fireEvent.change(input, { target: { value: 'https://example.com/product' } });
    expect(input.value).toBe('https://example.com/product');
  });

  it('should handle valid form submission and add item to wishlist', async () => {
    // Mock fetch response
    global.fetch = jest.fn().mockResolvedValueOnce({
      ok: true,
      json: async () => ({ data: { title: 'Test Product', image: 'test.jpg', url: 'https://example.com/product' } }),
    }) as jest.Mock;

    render(<WishlistForm />);
    const input = screen.getByPlaceholderText('Enter product URL');
    const button = screen.getByRole('button', { name: /add to wishlist/i });

    fireEvent.change(input, { target: { value: 'https://example.com/product' } });
    fireEvent.click(button);

    await waitFor(() => {
      expect(screen.getByText('Test Product')).toBeInTheDocument();
    });
    expect(global.fetch).toHaveBeenCalledWith('/api/wishlist', expect.any(Object));
  });

  it('should display an error message if submission fails', async () => {
    global.fetch = jest.fn().mockResolvedValueOnce({
      ok: false,
      json: async () => ({ error: 'Failed to add item to wishlist' }),
    }) as jest.Mock;

    render(<WishlistForm />);
    const input = screen.getByPlaceholderText('Enter product URL');
    const button = screen.getByRole('button', { name: /add to wishlist/i });

    fireEvent.change(input, { target: { value: 'https://example.com/product' } });
    fireEvent.click(button);

    await waitFor(() => {
      expect(screen.getByText('Failed to add item to wishlist')).toBeInTheDocument();
    });
  });

  // it('should remove item from the wishlist when delete button is clicked', async () => {
  //   window.localStorage.setItem(
  //     'wishlist',
  //     JSON.stringify([{ title: 'Test Product', image: 'test.jpg', url: 'https://example.com/product' }])
  //   );

  //   render(<WishlistForm />);

  //   expect(screen.getByText('Test Product')).toBeInTheDocument();

  //   const deleteButton = screen.getByRole('button', { name: 'Delete item' });
  //   fireEvent.click(deleteButton);

  //   await waitFor(() => {
  //     expect(screen.queryByText('Test Product')).not.toBeInTheDocument();
  //   });

  //   expect(window.localStorage.getItem('wishlist')).toBe('[]');
  // });
});



