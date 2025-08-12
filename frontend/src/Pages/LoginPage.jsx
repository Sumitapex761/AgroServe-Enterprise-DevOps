// src/pages/LoginPage.jsx
import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Form, Button, Container, Row, Col, Card } from "react-bootstrap";
import "../styles/AuthPage.css"; // <-- new css file

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
  e.preventDefault();
  try {
    const res = await axios.post("http://localhost:8080/users/signin", {
      email,
      password,
    });

    console.log("🔐 Token Response:", res.data.jwt);

    // Store token
    localStorage.setItem("token", res.data.jwt);

    alert("Login Successful!");
    navigate("/");
  } catch (error) {
    console.error("Login failed:", error.response || error.message || error);
    alert("Invalid credentials");
  }
};


  return (
    <div className="auth-page d-flex align-items-center">
      <Container>
        <Row className="justify-content-center">
          <Col md={5}>
            <Card className="p-4 shadow-lg auth-card">
              <h3 className="text-center mb-4 text-success">Login</h3>
              <Form onSubmit={handleLogin}>
                <Form.Group controlId="email" className="mb-3">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Enter your email"
                    required
                  />
                </Form.Group>
                <Form.Group controlId="password" className="mb-4">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Enter your password"
                    required
                  />
                </Form.Group>
                <Button variant="success" type="submit" className="w-100">
                  Login
                </Button>
                <Button
                  variant="link"
                  onClick={() => navigate("/signup")}
                  className="w-100 mt-2"
                >
                  Don&apos;t have an account? Sign Up
                </Button>
              </Form>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
}