import React, { useEffect, useState } from "react";
import { Navbar, Nav, Container, Button, Dropdown, Image, Badge } from "react-bootstrap";
import { useNavigate, NavLink } from "react-router-dom";
import logo from "../assets/agro-serve-logo.jpg";
import defaultProfilePic from "../assets/userProfile.jpg";
import "../styles/Navbar.css";

function NavbarComponent() {
  const navigate = useNavigate();
  const isLoggedIn = localStorage.getItem("token") !== null;

  const user = {
    name: "John Doe",
    profilePic: defaultProfilePic,
  };

  const [notifications, setNotifications] = useState([]);

  // Fetch notifications from backend API - implement your API call here
  const fetchNotifications = async () => {
    try {
      // TODO: Replace with your API call
      // const res = await axios.get('/api/notifications');
      // setNotifications(res.data);
    } catch (err) {
      console.error("Failed to fetch notifications:", err);
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

  const markAsRead = async (id) => {
    try {
      // TODO: Call API to mark notification read
      // await axios.post(`/api/notifications/${id}/read`);
      setNotifications((prev) =>
        prev.map((n) => (n.id === id ? { ...n, isRead: true } : n))
      );
    } catch (err) {
      console.error("Failed to mark notification as read", err);
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
                <Dropdown align="end" className="me-3">
                  <Dropdown.Toggle
                    variant="link"
                    id="dropdown-notifications"
                    className="p-0 border-0 position-relative notification-bell"
                  >
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

                  <Dropdown.Menu
                    className="dropdown-menu-notifications p-2"
                    style={{ minWidth: "300px" }}
                  >
                    <h6 className="px-2">Notifications</h6>
                    <hr className="my-1" />
                    {notifications.length === 0 && (
                      <div className="text-center p-2 text-muted">
                        No notifications
                      </div>
                    )}
                    {notifications.map((notif) => (
                      <Dropdown.Item
                        key={notif.id}
                        onClick={() => markAsRead(notif.id)}
                        style={{
                          backgroundColor: notif.isRead ? "#f9f9f9" : "#e6f7ff",
                          fontWeight: notif.isRead ? "normal" : "600",
                          cursor: "pointer",
                        }}
                      >
                        <div>{notif.title}</div>
                        <small className="text-muted">{notif.message}</small>
                      </Dropdown.Item>
                    ))}
                  </Dropdown.Menu>
                </Dropdown>

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
                    <Dropdown.Item onClick={() => navigate("/profile")}>
                      Profile
                    </Dropdown.Item>
                    <Dropdown.Item onClick={() => navigate("/bookings")}>
                      My Bookings
                    </Dropdown.Item>
                    <Dropdown.Divider />
                    <Dropdown.Item onClick={handleLogout} className="text-danger">
                      Logout
                    </Dropdown.Item>
                  </Dropdown.Menu>
                </Dropdown>
              </>
            ) : (
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
            )}
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavbarComponent;
