const KEY = 'vitalflow_session';
export function saveSession(session) { localStorage.setItem(KEY, JSON.stringify(session)); }
export function getSession() { try { return JSON.parse(localStorage.getItem(KEY)); } catch { return null; } }
export function clearSession() { localStorage.removeItem(KEY); }
export function isAuthenticated() { return Boolean(getSession()?.token); }
