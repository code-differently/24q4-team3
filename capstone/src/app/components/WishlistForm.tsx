"use client";

import { useState, useEffect } from "react";

interface WishlistItem {
  title: string;
  image: string;
  url: string;
}

export default function WishlistForm() {
  const [url, setUrl] = useState<string>("");
  const [wishlist, setWishlist] = useState<WishlistItem[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    const storedWishlist = JSON.parse(localStorage.getItem("wishlist") || "[]");
    setWishlist(storedWishlist);
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    if (!url) return;

    setLoading(true);

    try {
      const response = await fetch("/api/wishlist", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ url }),
      });

      if (response.ok) {
        const { data }: { data: WishlistItem } = await response.json();
        console.log("New Item:", data);

        // Add the item to the wishlist
        setWishlist((prev) => {
          const updatedWishlist = [...prev, data];
          localStorage.setItem("wishlist", JSON.stringify(updatedWishlist));
          return updatedWishlist;
        });
        setUrl(""); // Reset URL input
      } else {
        const errorData = await response.json();
        setError(errorData.error || "Failed to add item to wishlist");
      }
    } catch (err) {
      console.error("Error:", err);
      setError("An unexpected error occurred");
    } finally {
      setLoading(false);
    }
  };


  const removeItem = async (url: string) => {
    try {
      // Send DELETE request to the backend
      const response = await fetch('/api/wishlist', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ url }), // Send the unique URL for deletion
      });
  
      if (response.ok) {
        // Update local state and localStorage
        setWishlist((prev) => {
          const updatedWishlist = prev.filter((item) => item.url !== url); // Match URL for deletion
          localStorage.setItem('wishlist', JSON.stringify(updatedWishlist));
          return updatedWishlist;
        });
      } else {
        const errorData = await response.json();
        console.error('Error removing item:', errorData.error);
        setError(errorData.error || 'Failed to remove item from wishlist');
      }
    } catch (err) {
      console.error('Error:', err);
      setError('An unexpected error occurred');
    }

  };

  return (
    <div className="w-full max-w-md p-6 bg-[#F1F1F1] rounded-lg">
      <h1 className="text-3xl font-bold mb-6 text-[#B91C1C] text-center">
        🎄 My Christmas Wishlist 🎄
      </h1>

      {/* URL Input */}
      <form
        onSubmit={handleSubmit}
        className={`flex flex-col gap-4 mb-6 ${
          error ? "bg-[#FEE2E2] border border-[#EF4444]" : "bg-white"
        } p-4 rounded shadow-sm`}
      >
        <input
          type="url"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          placeholder="Enter product URL"
          required
          className="p-3 border border-[#10B981] rounded bg-[#F1F1F1] text-[#10B981] placeholder-[#9CA3AF] focus:outline-none focus:ring-2 focus:ring-[#10B981]"
        />
        <button
          type="submit"
          disabled={loading}
          className="p-3 bg-[#10B981] text-white rounded disabled:opacity-50 hover:bg-[#047857] transition-all duration-300"
        >
          {loading ? "Adding..." : "Add to Wishlist"}
          {loading && (
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
          )}
        </button>
        {error && <p className="text-[#EF4444] text-center">{error}</p>}
      </form>

      {/* Wishlist items */}
      <div className="space-y-4 mt-4 max-h-96 overflow-auto">
        {wishlist.map((item, index) => (
          <div
            key={index}
            className="flex items-center justify-between p-4 border border-[#D1D5DB] rounded bg-[#F9FAFB] shadow-sm"
          >
            <div className="flex items-center gap-4">
              <img
                src={item.image || "/christmas-gift.png"}
                alt={item.title}
                width={70}
                height={70}
                className="object-cover rounded"
              />
              <a
                href={item.url}
                target="_blank"
                rel="noopener noreferrer"
                className="text-lg font-semibold text-[#B91C1C] hover:text-[#991B1B] transition-all duration-200"
              >
                {item.title}
              </a>
            </div>
            <button

              onClick={() => removeItem(item.url)}
              className="p-2 text-[#B91C1C] hover:text-[#991B1B] transition-all duration-200"
              aria-label="Delete item"

            >
              {/* Trash Icon (Heroicons) */}
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
                className="w-6 h-6"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M6 18L18 6M6 6l12 12"
                />
              </svg>
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}