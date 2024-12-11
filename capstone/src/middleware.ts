import { clerkMiddleware, ClerkMiddlewareAuth } from '@clerk/nextjs/server';
import { NextResponse } from 'next/server';

const isPublicRoute = (path: string) => {
  return ['/sign-in', '/sign-up', '/invite'].some((publicPath) => path.startsWith(publicPath));
};

export default clerkMiddleware((req: ClerkMiddlewareAuth) => {

  const { pathname } = req.nextUrl; 

  if (isPublicRoute(pathname)) {
    return NextResponse.next(); 
  }

  
  return NextResponse.redirect('/sign-in');
});



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
