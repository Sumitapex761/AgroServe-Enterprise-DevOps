// src/components/NavbarComponent.jsx
import React from "react";
import { Navbar, Nav, Container, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function NavbarComponent() {
  const navigate = useNavigate();

  return (
    <Navbar bg="dark" variant="dark" expand="lg" sticky="top">
      <Container>
        <Navbar.Brand href="/">Agro Services</Navbar.Brand>
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
              className="me-2"
              onClick={() => navigate("/login")}
            >
              Login
            </Button>
            <Button
              variant="light"
              size="sm"
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
