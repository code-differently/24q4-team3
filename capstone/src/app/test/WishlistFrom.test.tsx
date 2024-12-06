
import { POST } from '../api/wishlist/route';
import { NextResponse } from 'next/server';
import { jest } from '@jest/globals';
import axios from 'axios';
import ogs from 'open-graph-scraper';

jest.mock('open-graph-scraper', () => jest.fn());
jest.mock('axios');

// Explicitly type the mock for axios.get
(axios.get as jest.Mock).mockResolvedValue({
  data: '<meta property="product:price:amount" content="99.99"><meta property="og:image" content="fallback-image.jpg">',
});

describe('POST /api/wishlist', () => {
  it('should save a wishlist item successfully', async () => {
    // Mock Open Graph data
    (ogs as jest.MockedFunction<typeof ogs>).mockResolvedValue({
      error: false,
      result: {
        ogTitle: 'Mock Title',
        ogImage: { url: 'mock-image-url.jpg' },
      },
    });

    // Mock Axios for fallback
    (axios.get as jest.Mock).mockResolvedValue({
      data: '<meta property="product:price:amount" content="99.99"><meta property="og:image" content="fallback-image.jpg">',
    });

    const request = {
      json: jest.fn().mockResolvedValue({ url: 'http://localhost:3000/' }),
    } as unknown as Request;

    const response = await POST(request);

    expect(response).toBeInstanceOf(NextResponse);

    const json = await response.json();

    expect(json).toHaveProperty('message', 'Wishlist item saved successfully');
    expect(json.data).toEqual({
      title: 'Mock Title',
      price: '99.99',
      image: 'mock-image-url.jpg',
      url: 'http://localhost:3000/',
    });
  });
});