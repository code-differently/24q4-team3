import type { Config } from "tailwindcss";

export default {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        background: "var(--background)", 
        foreground: "var(--foreground)", 
        gold: "#D78628", 
        pink: "#FF69B4",
      },
      animation: {
        blink: "blink 1s infinite", 
      },
      keyframes: {
        blink: {
          "0%, 100%": { opacity: "1" }, 
          "50%": { opacity: "0" }, 
        },
      },
    },
  },
  plugins: [],
} satisfies Config;

