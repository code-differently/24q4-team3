import { clerkMiddleware, ClerkMiddlewareAuth } from '@clerk/nextjs/server';
import { NextResponse } from 'next/server';

const isProtectedRoute = createRouteMatcher(['/invite', '/view-invitation', '/api'])

export default clerkMiddleware(async (auth, req) => {
  if (isProtectedRoute(req)) await auth.protect()
})

export const config = {
  matcher: [
    '/_next/*',  // Match any Next.js internal assets
    '/**/*.html', // Match all HTML files
    '/**/*.css',  // Match all CSS files
    '/**/*.js',   // Match all JavaScript files
    '/**/*.jpeg', // Match all JPEG files
    '/**/*.png',  // Match all PNG files
    '/**/*.gif',  // Match all GIF files
    '/**/*.svg',  // Match all SVG files
    '/**/*.ttf',  // Match all TTF files
    '/**/*.woff2',// Match all WOFF2 files
    '/**/*.ico',  // Match all ICO files
    '/**/*.csv',  // Match all CSV files
    '/**/*.docx', // Match all DOCX files
    '/**/*.xlsx', // Match all XLSX files
    '/**/*.zip',  // Match all ZIP files
    '/**/*.webmanifest' // Match all webmanifest files
  ],
};
