import { NextResponse } from 'next/server';
import axios from 'axios';
import * as cheerio from 'cheerio';

interface WishlistItem {
  title: string;
  url: string;
}


async function getTitleFromHTML(url: string) {
  try {
   
    const { data } = await axios.get(url, {
      headers: { 
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36' 
      },
      timeout: 5000, 
    });


    const $ = cheerio.load(data);

   
    let title = $('meta[property="og:title"]').attr('content');

   
    if (!title) {
      title = $('title').text().trim();
    }

   
    return title || 'No Title Found';
  } catch (error: unknown) {
    if (error instanceof Error) {
      console.error('Error fetching title from HTML:', error.message);
    } else {
      console.error('Unknown error:', error);
    }
    return 'No Title Found';
  }
}

export async function POST(request: Request) {
  try {
    const { url } = await request.json();

    if (!url) {
      return NextResponse.json({ error: 'URL is required' }, { status: 400 });
    }

   
    const title = await getTitleFromHTML(url);

   
    const newItem: WishlistItem = {
      title,
      url,
    };

   
    const clientPromise = import('../../lib/mongodb').then((mod) => mod.default);
    const client = await clientPromise;
    const database = client.db('wishlistDB');
    const collection = database.collection('wishlistItems');
    await collection.insertOne(newItem);

    return NextResponse.json({ message: 'Wishlist item saved successfully', data: newItem }, { status: 201 });

  } catch (error: unknown) {
    if (error instanceof Error) {
      console.error('Error in /api/wishlist:', error.message, error.stack);
    } else {
      console.error('Unknown error in /api/wishlist:', error);
    }
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}

export async function DELETE(request: Request) {
  try {
    const { url } = await request.json();

    if (!url) {
      return new Response(JSON.stringify({ error: 'URL is required' }), { status: 400 });
    }

    
    const clientPromise = import('../../lib/mongodb').then(mod => mod.default);
    const client = await clientPromise;
    const database = client.db('wishlistDB');
    const collection = database.collection('wishlistItems');

    const deleteResult = await collection.deleteOne({ url });

    if (deleteResult.deletedCount === 0) {
      return new Response(JSON.stringify({ error: 'Item not found' }), { status: 404 });
    }

    return new Response(JSON.stringify({ message: 'Item deleted successfully' }), { status: 200 });
  } catch (error: unknown) {
    if (error instanceof Error) {
      console.error('Error in DELETE /api/wishlist:', error.message);
    } else {
      console.error('Unknown error in DELETE /api/wishlist:', error);
    }
    return new Response(JSON.stringify({ error: 'Internal Server Error' }), { status: 500 });
  }
}
