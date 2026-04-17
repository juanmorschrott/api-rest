import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';
import App from './App.tsx';
import keycloak from './keycloak';
import 'bootswatch/dist/flatly/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';

keycloak.init({ onLoad: 'login-required', checkLoginIframe: false }).then(authenticated => {
  if (!authenticated) {
    window.location.reload();
  } else {
    // Interceptar cada llamada a la API para enviar el token
    axios.interceptors.request.use(
      config => {
        if (keycloak.token) {
          config.headers.Authorization = `Bearer ${keycloak.token}`;
        }
        return config;
      },
      error => Promise.reject(error)
    );

    // Renderizar la app solo cuando estamos logados
    createRoot(document.getElementById('root')!).render(
      <StrictMode>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </StrictMode>,
    );
  }
}).catch(err => {
  console.error("Error al inicializar Keycloak", err);
});
