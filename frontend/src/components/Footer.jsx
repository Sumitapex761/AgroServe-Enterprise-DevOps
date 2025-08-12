// src/components/Footer.jsx
import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import { FaFacebookF, FaTwitter, FaInstagram, FaLeaf } from "react-icons/fa";
import "../styles/Footer.css";

function Footer() {
  return (
    <footer className="agro-footer">
      <Container>
        <Row className="footer-top">
          <Col md={4} className="footer-about">
            <h4>
              <FaLeaf className="leaf-icon" /> Agro Serve
            </h4>
            <p>
              Connecting farmers with reliable agricultural service providers.
              Empowering rural communities for a sustainable future.
            </p>
          </Col>

          <Col md={4} className="footer-links">
            <h5>Quick Links</h5>
            <ul>
              <li><a href="/">Home</a></li>
              <li><a href="/services">Services</a></li>
              <li><a href="/about">About Us</a></li>
              <li><a href="/contact">Contact</a></li>
            </ul>
          </Col>

          <Col md={4} className="footer-contact">
            <h5>Contact Us</h5>
            <p>Email: support@agroserve.com</p>
            <p>Phone: +91 98765 43210</p>
            <div className="social-icons">
              <a href="#"><FaFacebookF /></a>
              <a href="#"><FaTwitter /></a>
              <a href="#"><FaInstagram /></a>
            </div>
          </Col>
        </Row>

        <Row className="footer-bottom">
          <Col className="text-center">
            <p>© {new Date().getFullYear()} Agro Serve. All Rights Reserved.</p>
          </Col>
        </Row>
      </Container>
    </footer>
  );
}

export default Footer;
