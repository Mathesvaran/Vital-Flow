import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { login } from '../services/authApi';
import { saveSession } from '../services/session';

export default function LoginPage() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ identifier: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const submit = async event => { event.preventDefault(); setError(''); setLoading(true); try { const data = await login(form); saveSession(data); navigate(routeFor(data.role)); } catch (e) { setError(e.message); } finally { setLoading(false); } };
  return <AuthLayout title="Welcome back" subtitle="Sign in to continue your VitalFlow journey."><form onSubmit={submit} className="space-y-5"><Field label="Email or phone"><input className="input" value={form.identifier} onChange={e => setForm({ ...form, identifier: e.target.value })} required placeholder="you@example.com or phone" /></Field><Field label="Password"><input className="input" type="password" value={form.password} onChange={e => setForm({ ...form, password: e.target.value })} required placeholder="Your password" /></Field>{error && <ErrorMessage>{error}</ErrorMessage>}<button className="primary-button w-full" disabled={loading}>{loading ? 'Signing in...' : 'Login'}</button><p className="text-center text-sm text-slate-500">New to VitalFlow? <Link className="font-semibold text-primary" to="/register/donor">Create an account</Link></p></form></AuthLayout>;
}

export function AuthLayout({ title, subtitle, children }) { return <div className="min-h-screen bg-blue-50 px-6 py-10"><div className="mx-auto max-w-lg"><Link to="/" className="mb-8 block text-center text-2xl font-bold text-primary">💧 VitalFlow</Link><div className="rounded-2xl border bg-white p-7 shadow-sm md:p-10"><h1 className="text-3xl font-bold">{title}</h1><p className="mt-2 text-slate-500">{subtitle}</p><div className="mt-8">{children}</div></div></div></div>; }
export function Field({ label, children }) { return <label className="block text-sm font-semibold text-slate-700">{label}<div className="mt-2">{children}</div></label>; }
export function ErrorMessage({ children }) { return <p className="rounded-lg bg-red-50 p-3 text-sm text-red-700">{children}</p>; }
function routeFor(role) { return role === 'DONOR' ? '/donor/dashboard' : role === 'HOSPITAL' ? '/hospital/dashboard' : '/blood-bank/dashboard'; }
