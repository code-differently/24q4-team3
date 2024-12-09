'use client';

import { useState, useEffect } from 'react';

interface WishlistItem {
  title: string;
  image: string;
  url: string;
}

export default function WishlistForm() {
  const [url, setUrl] = useState<string>('');
  const [wishlist, setWishlist] = useState<WishlistItem[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    const storedWishlist = JSON.parse(localStorage.getItem('wishlist') || '[]');
    setWishlist(storedWishlist);
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    if (!url) return;

    setLoading(true);

    try {
      const response = await fetch('/api/wishlist', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ url }),
      });

      if (response.ok) {
        const { data }: { data: WishlistItem } = await response.json();
        console.log('New Item:', data);

        // Add the item to the wishlist
        setWishlist((prev) => {
          const updatedWishlist = [...prev, data];
          localStorage.setItem('wishlist', JSON.stringify(updatedWishlist)); 
          return updatedWishlist;
        });
        setUrl(''); // Reset URL input
      } else {
        const errorData = await response.json();
        setError(errorData.error || 'Failed to add item to wishlist');
      }
    } catch (err) {
      console.error('Error:', err);
      setError('An unexpected error occurred');
    } finally {
      setLoading(false);
    }
  };

  const removeItem = (index: number) => {
    const updatedWishlist = wishlist.filter((_, i) => i !== index);
    setWishlist(updatedWishlist);
    localStorage.setItem('wishlist', JSON.stringify(updatedWishlist)); // Update localStorage
  };

  return (
    <div className="w-full max-w-md p-6 bg-gradient-to-br from-red-200 via-white to-green-200 rounded-lg shadow-xl">
      <h1 className="text-3xl font-bold mb-6 text-green-800 text-center">🎄 My Christmas Wishlist 🎄</h1>

      {/* URL Input */}
      <form onSubmit={handleSubmit} className={`flex flex-col gap-4 mb-6 ${error ? 'bg-red-200 border border-red-400' : 'bg-white'} p-4 rounded shadow-sm`}>
        <input
          type="url"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          placeholder="Enter product URL"
          required
          className="p-3 border border-green-500 rounded bg-red-200 text-green-800 placeholder-green-700 focus:outline-none focus:ring-2 focus:ring-green-600"
        />
        <button
          type="submit"
          disabled={loading}
          className="p-3 bg-green-600 text-white rounded disabled:opacity-50 hover:bg-green-500 transition-all duration-300"
        >
  
          {loading ? 'Adding...' : 'Add to Wishlist'}
          {loading ? (
            <svg
              className="animate-spin h-10 w-10 text-black"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
            >
              <circle
                className="opacity-25"
                cx="12"
                cy="12"
                r="10"
                stroke="currentColor"
                strokeWidth="4"
              ></circle>
              <path
                className="opacity-75"
                fill="currentColor"
                d="M4 12a8 8 0 018-8v8H4z"
              ></path>
            </svg>
          ) : (
            ''
          )}

        </button>
        {error && <p className="text-red-500 text-center">{error}</p>}
      </form>

      {/* Wishlist items */}
      <div className="space-y-4 mt-4">
        {wishlist.map((item, index) => (
          <div key={index} className="flex items-center gap-4 p-4 border border-gray-200 rounded bg-green-50">
            <img
              src={item.image || '/christmas-gift.png'} 
              alt={item.title}
              width={100}
              height={100}
              className="object-cover rounded"
            />
         <div className="flex flex-col items-start space-y-2">
            <a
             href={item.url}
             target="_blank"
             rel="noopener noreferrer"
             className="text-lg font-semibold text-red-600 hover:text-white hover:bg-green-600 rounded p-2 transition-all duration-300"
             >
              {item.title}
            </a>
        </div>
            <button
              onClick={() => removeItem(index)}
              className="ml-4 p-2 bg-red-600 text-white rounded-full hover:bg-red-700 focus:outline-none transition-all duration-200"
            >
              Remove
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}




