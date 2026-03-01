import {createRoot} from 'react-dom/client'
import {StrictMode} from 'react'
import {onCLS, onINP, onLCP} from 'web-vitals'
import App from './App'

let container = document.getElementById("app")!;
let root = createRoot(container)
root.render(
  <StrictMode>
    <App/>
  </StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
onCLS(console.log)
onINP(console.log)
onLCP(console.log)
