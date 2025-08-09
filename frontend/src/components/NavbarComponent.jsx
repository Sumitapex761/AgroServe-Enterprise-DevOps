import React, { useEffect, useState } from "react";
import { Navbar, Nav, Container, Button, Dropdown, Image, Badge } from "react-bootstrap";
import { useNavigate, NavLink } from "react-router-dom";
import logo from "../assets/agro-serve-logo.jpg";
import defaultProfilePic from "../assets/userProfile.jpg";
import "../styles/Navbar.css";

import { getNotifications, markNotificationRead, approveBooking } from "../api/axios";

function NavbarComponent() {
  const navigate = useNavigate();
  const isLoggedIn = localStorage.getItem("token") !== null;

  const user = {
    name: "John Doe",
    profilePic: defaultProfilePic,
  };

  const [notifications, setNotifications] = useState([]);

  // Fetch notifications from backend API
  const fetchNotifications = async () => {
    try {
      const res = await getNotifications();
      setNotifications(res.data);
    } catch (err) {
      console.error("Failed to fetch notifications:", err);
      setNotifications([]);
    }
  };

  useEffect(() => {
    if (isLoggedIn) {
      fetchNotifications();
    } else {
      setNotifications([]);
    }
  }, [isLoggedIn]);

  const unreadCount = notifications.filter((n) => !n.isRead).length;

  const handleNotificationClick = async (notif) => {
    try {
      // Mark notification as read in backend (needs to be implemented in backend)
      await markNotificationRead(notif.id);
      // Update local state to mark as read
      setNotifications((prev) =>
        prev.map((n) => (n.id === notif.id ? { ...n, isRead: true } : n))
      );

      // Confirm approval if notification is a booking request
      if (window.confirm("Approve this booking request?")) {
        await approveBooking(notif.bookingId);
        alert("Booking approved!");
        // Refresh notifications after approval
        fetchNotifications();
      }
    } catch (err) {
      console.error("Error handling notification click:", err);
      alert("Failed to process notification action");
    }
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
            <Nav.Link as={NavLink} to="/">
              Home
            </Nav.Link>
            <Nav.Link as={NavLink} to="/services">
              Services
            </Nav.Link>
            <Nav.Link as={NavLink} to="/bookings">
              Bookings
            </Nav.Link>
            <Nav.Link as={NavLink} to="/contact">
              Contact
            </Nav.Link>
            <Nav.Link as={NavLink} to="/dashboard">
              Dashboard
            </Nav.Link>
          </Nav>

          <div className="d-flex ms-3 align-items-center">
            {isLoggedIn ? (
              <>
                {/* Notifications Dropdown - Minimal test version to confirm open/close */}
                <Dropdown align="end" className="me-3">
                  <Dropdown.Toggle id="dropdown-notifications" variant="link" className="p-0 border-0 position-relative notification-bell">
                    <i className="bi bi-bell"></i>
                    {unreadCount > 0 && (
                      <Badge
                        bg="danger"
                        pill
                        className="position-absolute top-0 start-100 translate-middle notification-badge"
                      >
                        {unreadCount}
                      </Badge>
                    )}
                  </Dropdown.Toggle>

                  <Dropdown.Menu className="dropdown-menu-notifications p-2" style={{ minWidth: "300px" }}>
                    <h6 className="px-2">Notifications</h6>
                    <hr className="my-1" />
                    {notifications.length === 0 ? (
                      <div className="text-center p-2 text-muted">No notifications</div>
                    ) : (
                      notifications.map((notif) => (
                        <Dropdown.Item
                          key={notif.id}
                          onClick={() => handleNotificationClick(notif)}
                          style={{
                            backgroundColor: notif.isRead ? "#f9f9f9" : "#e6f7ff",
                            fontWeight: notif.isRead ? "normal" : "600",
                            cursor: "pointer",
                          }}
                        >
                          <div>{notif.message}</div>
                          <small className="text-muted">{new Date(notif.sentAt).toLocaleString()}</small>
                        </Dropdown.Item>
                      ))
                    )}
                  </Dropdown.Menu>
                </Dropdown>

                {/* User Dropdown */}
                <Dropdown align="end">
                  <Dropdown.Toggle variant="link" id="dropdown-user" className="p-0 border-0 dropdown-toggle-avatar">
                    <Image src={user.profilePic} roundedCircle width="42" height="42" className="profile-icon" />
                  </Dropdown.Toggle>

                  <Dropdown.Menu className="dropdown-menu-custom">
                    <Dropdown.Item onClick={() => navigate("/profile")}>Profile</Dropdown.Item>
                    <Dropdown.Item onClick={() => navigate("/bookings")}>My Bookings</Dropdown.Item>
                    <Dropdown.Divider />
                    <Dropdown.Item onClick={handleLogout} className="text-danger">Logout</Dropdown.Item>
                  </Dropdown.Menu>
                </Dropdown>
              </>
            ) : (
              <>
                <Button variant="outline-light" size="sm" className="me-2 nav-btn" onClick={() => navigate("/login")}>
                  Login
                </Button>
                <Button variant="warning" size="sm" className="nav-btn" onClick={() => navigate("/signup")}>
                  Sign Up
                </Button>
              </>
            )}
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavbarComponent;
