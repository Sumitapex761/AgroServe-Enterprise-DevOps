// src/pages/SignupPage.jsx
import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Form, Button, Container, Row, Col, Card } from "react-bootstrap";
import "../styles/AuthPage.css"; // using same css as login

export default function SignupPage() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [phone, setPhone] = useState("");
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/auth/signup", {
        name,
        email,
        password,
        phone,
      });
      alert("Signup Successful!");
      navigate("/login");
    } catch (error) {
      alert("Error during signup");
    }
  };

  return (
    <div className="auth-page d-flex align-items-center">
      <Container>
        <Row className="justify-content-center">
          <Col md={5}>
            <Card className="p-4 shadow-lg auth-card">
              <h3 className="text-center mb-4 text-success">Sign Up</h3>
              <Form onSubmit={handleSignup}>
                <Form.Group controlId="name" className="mb-3">
                  <Form.Label>Name</Form.Label>
                  <Form.Control
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder="Enter your name"
                    required
                  />
                </Form.Group>
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
                <Form.Group controlId="phone" className="mb-3">
                  <Form.Label>Phone</Form.Label>
                  <Form.Control
                    type="text"
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                    placeholder="Enter your phone number"
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
                  Sign Up
                </Button>
                <Button
                  variant="link"
                  onClick={() => navigate("/login")}
                  className="w-100 mt-2"
                >
                  Already have an account? Login
                </Button>
              </Form>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
