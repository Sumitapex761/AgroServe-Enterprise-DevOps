// src/components/NavbarComponent.jsx
import React from "react";
import { Navbar, Nav, Container, Button, Dropdown, Image } from "react-bootstrap";
import { useNavigate, NavLink } from "react-router-dom";
import logo from "../assets/agro-serve-logo.jpg";
import defaultProfilePic from "../assets/userProfile.jpg";
import "../styles/Navbar.css";

function NavbarComponent() {
  const navigate = useNavigate();
  const isLoggedIn = localStorage.getItem("token") !== null;

  const user = {
    name: "John Doe",
    profilePic: defaultProfilePic
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <Navbar expand="lg" sticky="top" className="custom-navbar shadow-sm">
      <Container>
        <Navbar.Brand
          as={NavLink}
          to="/"
          className="brand-logo d-flex align-items-center"
        >
          <img src={logo} alt="Agro Services Logo" className="navbar-logo" />
          <span className="ms-2 brand-text">Agro Serve</span>
        </Navbar.Brand>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />

        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto nav-links">
            <Nav.Link as={NavLink} to="/">Home</Nav.Link>
            <Nav.Link as={NavLink} to="/services">Services</Nav.Link>
            <Nav.Link as={NavLink} to="/bookings">Bookings</Nav.Link>
            <Nav.Link as={NavLink} to="/contact">Contact</Nav.Link>
            <Nav.Link as={NavLink} to="/dashboard">Dashboard</Nav.Link>
          </Nav>

          <div className="d-flex ms-3">
            {!isLoggedIn ? (
              <>
                <Button
                  variant="outline-light"
                  size="sm"
                  className="me-2 nav-btn"
                  onClick={() => navigate("/login")}
                >
                  Login
                </Button>
                <Button
                  variant="warning"
                  size="sm"
                  className="nav-btn"
                  onClick={() => navigate("/signup")}
                >
                  Sign Up
                </Button>
              </>
            ) : (
              <Dropdown align="end">
                <Dropdown.Toggle
                  variant="link"
                  id="dropdown-user"
                  className="p-0 border-0 dropdown-toggle-avatar"
                >
                  <Image
                    src={user.profilePic}
                    roundedCircle
                    width="42"
                    height="42"
                    className="profile-icon"
                  />
                </Dropdown.Toggle>

                <Dropdown.Menu className="dropdown-menu-custom">
                  <Dropdown.Item onClick={() => navigate("/profile")}>Profile</Dropdown.Item>
                  <Dropdown.Item onClick={() => navigate("/bookings")}>My Bookings</Dropdown.Item>
                  <Dropdown.Divider />
                  <Dropdown.Item onClick={handleLogout} className="text-danger">
                    Logout
                  </Dropdown.Item>
                </Dropdown.Menu>
              </Dropdown>
            )}
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavbarComponent;
