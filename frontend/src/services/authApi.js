const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api';
async function request(path, options = {}) { const response = await fetch(`${API_BASE_URL}${path}`, { headers: { 'Content-Type': 'application/json', ...(options.headers || {}) }, ...options }); const body = await response.json().catch(() => ({})); if (!response.ok) throw new Error(body.message || body.error || 'Something went wrong.'); return body; }
export function register(payload) { return request('/auth/register', { method: 'POST', body: JSON.stringify(payload) }); }
export function login(payload) { return request('/auth/login', { method: 'POST', body: JSON.stringify(payload) }); }
export function me(token) { return request('/auth/me', { headers: { Authorization: `Bearer ${token}` } }); }
