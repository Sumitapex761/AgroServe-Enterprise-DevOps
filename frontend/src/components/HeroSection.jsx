// src/components/HeroSection.jsx
import React from "react";
import heroImage from "../assets/agro_1.jpg";  // <-- imported from src/assets
import "../styles/HeroSection.css";

function HeroSection() {
  return (
    <section 
      className="hero-section"
      style={{
        backgroundImage: `url(${heroImage})`
      }}
    >
      <div className="overlay"></div>
      <div className="hero-content">
        <h1>Welcome to Agro Services</h1>
        <p>Connecting Farmers with Agricultural Service Providers.</p>
        <button className="btn btn-success btn-lg">Explore Services</button>
      </div>
    </section>
  );
}

export default HeroSection;
