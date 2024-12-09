import {
    ClerkProvider,
    SignInButton,
    SignedIn,
    SignedOut,
    UserButton
  } from '@clerk/nextjs';
  import '@/styles/globals.css';
  import type { AppProps } from 'next/app';
  
  function App({ Component, pageProps }: AppProps) {
    return (
      <ClerkProvider>
        <SignedOut>
          <SignInButton />
        </SignedOut>
        <SignedIn>
          <UserButton />
        </SignedIn>
        <Component {...pageProps} />
      </ClerkProvider>
    );
  }
  
  export default App;
  