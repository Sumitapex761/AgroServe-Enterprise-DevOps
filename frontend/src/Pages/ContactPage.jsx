import React, { useState } from "react";
import { Container, Form, Button, Alert, Card } from "react-bootstrap";
import { EnvelopeFill, PersonFill, ChatDotsFill } from "react-bootstrap-icons";
import "../styles/ContactPage.css";

function ContactPage() {
  const [formData, setFormData] = useState({ name: "", email: "", message: "" });
  const [submitted, setSubmitted] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // TODO: integrate with backend
    console.log("Contact form data:", formData);
    setSubmitted(true);
    setTimeout(() => setSubmitted(false), 3000);
  };

  return (
    <div className="contact-page py-5">
      <Container style={{ maxWidth: "650px" }}>
        <h2 className="text-center mb-4 section-title">Get in Touch</h2>

        {submitted && (
          <Alert variant="success" className="text-center">
            Your message has been sent successfully!
          </Alert>
        )}

        <Card className="contact-card shadow-sm p-4">
          <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="formName">
              <Form.Label>
                <PersonFill className="me-2 text-success" />
                Name
              </Form.Label>
              <Form.Control
                type="text"
                name="name"
                placeholder="Enter your name"
                required
                value={formData.name}
                onChange={handleChange}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formEmail">
              <Form.Label>
                <EnvelopeFill className="me-2 text-success" />
                Email
              </Form.Label>
              <Form.Control
                type="email"
                name="email"
                placeholder="Enter your email"
                required
                value={formData.email}
                onChange={handleChange}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formMessage">
              <Form.Label>
                <ChatDotsFill className="me-2 text-success" />
                Message
              </Form.Label>
              <Form.Control
                as="textarea"
                name="message"
                rows={4}
                placeholder="Write your message..."
                required
                value={formData.message}
                onChange={handleChange}
              />
            </Form.Group>

            <Button variant="success" type="submit" className="w-100">
              Send Message
            </Button>
          </Form>
        </Card>
      </Container>
    </div>
  );
}

export default ContactPage;