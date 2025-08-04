import React, { useState } from "react";
import { Container, Form, Button, Alert } from "react-bootstrap";

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
  };

  return (
    <Container className="py-5" style={{ maxWidth: "600px" }}>
      <h2 className="text-center mb-4">Contact Us</h2>
      {submitted && <Alert variant="success">Message sent successfully!</Alert>}
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3" controlId="formName">
          <Form.Label>Name</Form.Label>
          <Form.Control type="text" name="name" required value={formData.name} onChange={handleChange} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formEmail">
          <Form.Label>Email</Form.Label>
          <Form.Control type="email" name="email" required value={formData.email} onChange={handleChange} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formMessage">
          <Form.Label>Message</Form.Label>
          <Form.Control as="textarea" name="message" rows={4} required value={formData.message} onChange={handleChange} />
        </Form.Group>

        <Button variant="primary" type="submit" className="w-100">
          Send
        </Button>
      </Form>
    </Container>
  );
}

export default ContactPage;
