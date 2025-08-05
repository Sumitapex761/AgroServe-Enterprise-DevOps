// src/components/CallToAction.jsx
import React from "react";
import { Button } from "react-bootstrap";
import "../styles/CallToAction.css";   // <-- Correct CSS import

function CallToAction() {
  return (
    <section className="cta-section text-center py-5">
      <div className="container">
        <h2>Ready to Experience Agro Services?</h2>
        <p>Sign up today and start booking trusted agricultural services.</p>
        <Button variant="light" size="lg" className="mt-3">
          Sign Up Now
        </Button>
      </div>
    </section>
  );
}

export default CallToAction;
