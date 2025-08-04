import React from "react";
import heroImage from "../assets/agro_1.jpg";

function HeroSection() {
  const heroStyle = {
    backgroundImage: `url(${heroImage})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    height: "100vh",          // <-- Full Screen Height
    width: "100%",            // <-- Full Width
    display: "flex",          // Center Content
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    color: "white",
    textAlign: "center",
  };

  return (
    <section style={heroStyle}>
      <h1>Welcome to Agro Services</h1>
      <p>Connecting Farmers with Agricultural Service Providers.</p>
      <button className="btn btn-success btn-lg">Explore Services</button>
    </section>
  );
}

export default HeroSection;
