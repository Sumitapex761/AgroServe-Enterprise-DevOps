// src/components/ServicesSection.jsx
import React from "react";
import { Card, Button, Container, Row, Col } from "react-bootstrap";
import "./ServicesSection.css";

const services = [
  {
    title: "Irrigation Setup",
    description: "Efficient irrigation system installation",
    price: "₹4000",
  },
  {
    title: "Soil Testing",
    description: "Get soil tested for best crop yield",
    price: "₹1500",
  },
  {
    title: "Fertilizer Supply",
    description: "Organic and chemical fertilizers",
    price: "₹1000",
  },
  {
    title: "Farm Equipment Rental",
    description: "Affordable rentals",
    price: "₹1200/day",
  },
];

function ServicesSection() {
  return (
    <div className="services-section py-5">
      <Container>
        <h2 className="text-center mb-5">Our Featured Services</h2>
        <Row>
          {services.map((service, idx) => (
            <Col md={6} lg={3} key={idx} className="mb-4">
              <Card className="service-card h-100 shadow-sm">
                <Card.Body>
                  <Card.Title>{service.title}</Card.Title>
                  <Card.Text>{service.description}</Card.Text>
                  <Card.Text>
                    <strong>Price:</strong> {service.price}
                  </Card.Text>
                  <Button variant="success" className="w-100">
                    Book Now
                  </Button>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      </Container>
    </div>
  );
}

export default ServicesSection;
