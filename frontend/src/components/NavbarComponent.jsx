// src/components/NavbarComponent.jsx
import React from "react";
import { Navbar, Nav, Container, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import logo from "../assets/agro-serve-logo.jpg"; // <-- Your logo path
import "../styles/Navbar.css";

function NavbarComponent() {
  const navigate = useNavigate();

  return (
    <Navbar expand="lg" sticky="top" className="custom-navbar">
      <Container>
        <Navbar.Brand href="/" className="brand-logo">
          <img
            src={logo}
            alt="Agro Services Logo"
            className="navbar-logo"
          />
          <span>Agro Services</span>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto">
            <Nav.Link href="/">Home</Nav.Link>
            <Nav.Link href="/services">Services</Nav.Link>
            <Nav.Link href="/bookings">Bookings</Nav.Link>
            <Nav.Link href="/contact">Contact</Nav.Link>
            <Nav.Link href="/dashboard">Dashboard</Nav.Link>
          </Nav>
          <div className="d-flex ms-3">
            <Button
              variant="outline-light"
              size="sm"
              className="me-2 nav-btn"
              onClick={() => navigate("/login")}
            >
              Login
            </Button>
            <Button
              variant="light"
              size="sm"
              className="nav-btn"
              onClick={() => navigate("/signup")}
            >
              Sign Up
            </Button>
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavbarComponent;
