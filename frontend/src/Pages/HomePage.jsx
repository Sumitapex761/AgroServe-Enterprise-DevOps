// src/pages/HomePage.jsx
import React from "react";
import HeroSection from "../components/HeroSection";
import ServicesSection from "../components/ServicesSection";
import CallToAction from "../components/CallToAction";

function HomePage() {
  return (
    <>
      <HeroSection />
      <ServicesSection />
      <CallToAction />
    </>
  );
}

export default HomePage;
