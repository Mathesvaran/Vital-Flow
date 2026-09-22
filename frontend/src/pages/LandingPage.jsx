import { Link } from 'react-router-dom';

const roleCards = [
  { key: 'donor', title: 'Become a Lifesaver', text: 'Manage your donations, discover nearby needs, and make every drop count.', button: 'Register as Donor', className: 'bg-white' },
  { key: 'hospital', title: 'Streamline Your Supply', text: 'Raise urgent blood requests and coordinate supply with confidence.', button: 'Hospital Registration', className: 'bg-navy text-white' },
  { key: 'blood-bank', title: 'Scale Your Impact', text: 'Manage inventory and coordinate with hospitals in real time.', button: 'Register Blood Bank', className: 'bg-blue-100' },
];

export default function LandingPage() {
  return <div className="min-h-screen bg-surface">
    <header className="sticky top-0 z-20 border-b bg-white/95 px-6 py-5 backdrop-blur md:px-12">
      <div className="mx-auto flex max-w-7xl items-center justify-between"><Link to="/" className="text-2xl font-bold text-primary">💧 VitalFlow</Link><nav className="hidden gap-7 text-sm text-slate-600 md:flex"><a href="#donors">Donors</a><a href="#hospitals">Hospitals/Recipients</a><a href="#about">About Us</a><a href="#contact">Contact</a></nav><Link to="/login" className="rounded-lg bg-primary px-5 py-2.5 text-sm font-semibold text-white">Login</Link></div>
    </header>
    <main>
      <section className="bg-blue-50 px-6 py-20 text-center md:py-28"><span className="inline-flex rounded-full bg-white px-4 py-2 text-sm font-semibold text-primary shadow-sm">● Emergency Need: O Negative</span><h1 className="mx-auto mt-7 max-w-4xl text-5xl font-bold tracking-tight md:text-7xl">Connecting Life, One Drop at a Time</h1><p className="mx-auto mt-6 max-w-2xl text-lg leading-8 text-slate-600">A simple, efficient blood management system connecting donors directly with hospitals in real time.</p></section>
      <section id="donors" className="grid md:grid-cols-3">{roleCards.map(card => <div key={card.key} className={`${card.className} p-8 text-center md:p-12`}><div className="mx-auto mb-6 text-6xl">{card.key === 'donor' ? '🩸' : card.key === 'hospital' ? '🏥' : '🏦'}</div><h2 className="text-3xl font-bold">{card.title}</h2><p className="mx-auto my-6 max-w-sm leading-7 opacity-80">{card.text}</p><Link to={`/register/${card.key}`} className="inline-block rounded-lg bg-primary px-6 py-3 text-sm font-bold text-white">{card.button}</Link></div>)}</section>
      <section id="about" className="mx-auto max-w-7xl px-6 py-20 md:px-12"><div className="grid gap-6 md:grid-cols-3"><div className="rounded-xl bg-white p-7 shadow-sm"><p className="text-4xl font-bold text-primary">150+</p><p className="mt-2 text-slate-600">Blood banks connected</p></div><div className="rounded-xl bg-white p-7 shadow-sm"><p className="text-4xl font-bold text-primary">45,000+</p><p className="mt-2 text-slate-600">Recipients served</p></div><div className="rounded-xl bg-white p-7 shadow-sm"><p className="text-4xl font-bold text-primary">2,500+</p><p className="mt-2 text-slate-600">Donors active today</p></div></div></section>
    </main>
    <footer id="contact" className="bg-navy px-6 py-10 text-white md:px-12"><div className="mx-auto flex max-w-7xl flex-col justify-between gap-4 md:flex-row"><div><p className="text-xl font-bold">VitalFlow</p><p className="mt-2 text-sm text-blue-100">Every drop counts.</p></div><p className="text-sm text-blue-100">support@vitalflow.org · © 2026 VitalFlow</p></div></footer>
  </div>;
}
