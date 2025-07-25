import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    proxy: {
      '/user': 'http://localhost:8080',
      '/lecture': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
      '/quiz': 'http://localhost:8080',
      '/quizOption': 'http://localhost:8080',
      '/answer': 'http://localhost:8080',
      '/discussion': 'http://localhost:8080',
      '/feedback': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
      '/files': 'http://localhost:8080'
    }
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
})
