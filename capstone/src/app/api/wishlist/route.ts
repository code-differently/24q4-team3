import { NextResponse } from 'next/server';
import ogs from 'open-graph-scraper';
import axios from 'axios';
import cheerio from 'cheerio';
import { MongoClient } from 'mongodb';

interface WishlistItem {
  title: string;
  price: string;
  image: string;
  url: string;
}

async function getPriceAndImageFallback(url: string) {
  try {
    const { data } = await axios.get(url);
    const $ = cheerio.load(data);

    // Scraping for price and image, with fallback to Christmas image
    const price = $('meta[property="product:price:amount"]').attr('content') || '';
    const image = $('meta[property="og:image"]').attr('content') || '/christmas-gift.png'; 
    return { price, image };
  } catch (error) {
    console.error('Error scraping the page:', error);
    return { price: '', image: '/christmas-gift.png' };  
  }
}

export async function POST(request: Request) {
  try {
    const { url } = await request.json();

    if (!url) {
      return NextResponse.json({ error: 'URL is required' }, { status: 400 });
    }

    // Fetch Open Graph data
    const { error, result } = await ogs({ url });

    if (error) {
      console.error('OGS error:', error);
      return NextResponse.json({ error: 'Failed to fetch metadata from the URL' }, { status: 500 });
    }

    // If result is undefined or missing critical data, fall back
    if (!result || !result.ogImage?.url) {
      const { price, image } = await getPriceAndImageFallback(url);
      const newItem: WishlistItem = {
        title: result?.ogTitle || 'No Title Found',
        price: price || 'Price not available',
        image,
        url,
      };
      
      // MongoDB interaction
      const clientPromise = import('../../lib/mongodb').then(mod => mod.default);
      const client = await clientPromise;
      const database = client.db('wishlistDB');
      const collection = database.collection('wishlistItems');
      const insertResult = await collection.insertOne(newItem);
      console.log('Inserted wishlist item:', insertResult);

      return NextResponse.json({ message: 'Wishlist item saved successfully', data: newItem }, { status: 201 });
    }

    // Process the result
    let image = result.ogImage?.url || '/christmas-gift.png';  
    let price = result.ogPriceAmount || 'Price not available';

    const newItem: WishlistItem = {
      title: result.ogTitle || 'No Title Found',
      price,
      image,
      url,
    };

    // MongoDB interaction
    const clientPromise = import('../../lib/mongodb').then(mod => mod.default);
    const client = await clientPromise;
    const database = client.db('wishlistDB');
    const collection = database.collection('wishlistItems');
    const insertResult = await collection.insertOne(newItem);
    console.log('Inserted wishlist item:', insertResult);

    return NextResponse.json({ message: 'Wishlist item saved successfully', data: newItem }, { status: 201 });

  } catch (error) {
    console.error('Error in /api/wishlist:', error);
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}





