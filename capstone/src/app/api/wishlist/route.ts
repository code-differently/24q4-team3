import { NextResponse } from 'next/server';
import ogs from 'open-graph-scraper';
import axios from 'axios';
import * as cheerio from 'cheerio';

interface WishlistItem {
  title: string;
  price: string;
  image: string;
  url: string;
}

// Helper to scrape fallback data
async function getPriceAndImageFallback(url: string) {
  try {
    const { data } = await axios.get(url, {
      headers: { 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36' },
      timeout: 10000, // Timeout of 10 seconds
    });
    const $ = cheerio.load(data);

    const price = $('meta[property="product:price:amount"]').attr('content') || 'Price not available';
    const image = $('meta[property="og:image"]').attr('content') || '/christmas-gift.png';
    return { price, image };
  } catch (error) {
    console.error('Error scraping fallback data:', error.message);
    return { price: 'Price not available', image: '/christmas-gift.png' };
  }
}

export async function POST(request: Request) {
  try {
    const { url } = await request.json();

    // Check if the URL is a valid string
    if (!url || typeof url !== 'string') {
      return NextResponse.json({ error: 'URL is required and must be a valid string' }, { status: 400 });
    }

    // Validate URL format
    try {
      new URL(url); // This will throw an error if the URL is invalid
    } catch (e) {
      return NextResponse.json({ error: 'Invalid URL format' }, { status: 400 });
    }

    // Use Open Graph Scraper
    const { error: ogError, result } = await ogs({
      url,
      headers: { 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36' },
      timeout: 10000, // Timeout of 10 seconds
    });

    const title = result?.ogTitle || 'No Title Found';
    let image = result?.ogImage?.url || '/christmas-gift.png';
    let price = 'Price not available'; // Open Graph doesn't standardize price properties

    // If OGS fails, use fallback scraping
    if (ogError || !result) {
      console.warn('OGS failed, falling back to manual scraping...');
      const fallback = await getPriceAndImageFallback(url);
      image = fallback.image;
      price = fallback.price;
    }

    const newItem: WishlistItem = { title, price, image, url };

    // Save to MongoDB
    const clientPromise = import('../../lib/mongodb').then((mod) => mod.default);
    const client = await clientPromise;
    const database = client.db('wishlistDB');
    const collection = database.collection('wishlistItems');
    const insertResult = await collection.insertOne(newItem);

    console.log('Inserted wishlist item:', insertResult);

    return NextResponse.json(
      { success: true, message: 'Wishlist item saved successfully', data: newItem },
      { status: 201 }
    );
  } catch (error) {
    console.error('Error in /api/wishlist:', error.message);
    return NextResponse.json({ success: false, error: 'Internal Server Error' }, { status: 500 });
  }
}







