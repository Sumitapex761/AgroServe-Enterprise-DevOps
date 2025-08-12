// src/components/HeroSection.jsx
import React from "react";
import { useNavigate } from "react-router-dom";
import heroImage from "../assets/agro_1.jpg";
import "../styles/HeroSection.css";

function HeroSection() {
  const navigate = useNavigate();

  const handleExploreClick = () => {
    navigate("/services"); // Navigate to services page
  };

  return (
    <section
      className="hero-section"
      style={{
        backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.6)), url(${heroImage})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "100vh",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        textAlign: "center",
        color: "#fff",
        position: "relative",
      }}
    >
      <div className="hero-content animate-fade-in">
        <h1 className="hero-title">Welcome to Agro Serve</h1>
        <p className="hero-subtitle">
          Connecting Farmers with Agricultural Service Providers.
        </p>
        <button
          onClick={handleExploreClick}
          className="hero-btn"
        >
          🚜 Explore Services
        </button>
      </div>
    </section>
  );
}

export default HeroSection;
