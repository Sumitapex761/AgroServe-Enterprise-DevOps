// src/components/CallToAction.jsx
import React from "react";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "../styles/CallToAction.css";

function CallToAction() {
  const navigate = useNavigate();

  const handleSignupClick = () => {
    navigate("/signup"); // Redirects to signup page
  };

  return (
    <section className="cta-section text-center py-5">
      <div className="container">
        <h2 className="cta-title">🌱 Ready to Grow with Agro Serve?</h2>
        <p className="cta-subtitle">
          Sign up today and get connected with trusted agricultural service providers in your area.
        </p>
        <Button
          variant="light"
          size="lg"
          className="cta-button mt-3"
          onClick={handleSignupClick}
        >
          🚜 Sign Up Now
        </Button>
      </div>
    </section>
  );
}

export default CallToAction;
