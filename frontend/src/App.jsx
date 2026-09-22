import { Navigate, Route, Routes } from 'react-router-dom';
import LandingPage from './pages/LandingPage';
import LoginPage from './pages/LoginPage';
import RegistrationPage from './pages/RegistrationPage';
import RegistrationSuccessPage from './pages/RegistrationSuccessPage';
import DashboardPage from './pages/DashboardPage';
import { isAuthenticated } from './services/session';
import './index.css';

function Protected({ children }) { return isAuthenticated() ? children : <Navigate to="/login" replace />; }
export default function App() { return <Routes><Route path="/" element={<LandingPage />} /><Route path="/login" element={<LoginPage />} /><Route path="/register/:role" element={<RegistrationPage />} /><Route path="/registration-success" element={<RegistrationSuccessPage />} /><Route path="/donor/dashboard" element={<Protected><DashboardPage role="Donor" /></Protected>} /><Route path="/hospital/dashboard" element={<Protected><DashboardPage role="Hospital" /></Protected>} /><Route path="/blood-bank/dashboard" element={<Protected><DashboardPage role="Blood Bank" /></Protected>} /><Route path="*" element={<Navigate to="/" replace />} /></Routes>; }
