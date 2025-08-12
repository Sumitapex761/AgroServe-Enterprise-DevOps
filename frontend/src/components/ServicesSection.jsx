import React from "react";
import { Card, Container, Row, Col } from "react-bootstrap";
import "../styles/ServicesSection.css";

import irrigationImg from "../assets/irrigation-set-up.jpg";
import soilTestingImg from "../assets/Soil-testing.avif";
import fertilizersImg from "../assets/FertilizersSupply.webp";
import farmEquipmentImg from "../assets/EquipmentRental.jpg";

const services = [
  {
    title: "Irrigation Setup",
    description:
      "Modern, efficient irrigation systems tailored to your crop needs for higher yield and water savings.",
    image: irrigationImg,
  },
  {
    title: "Soil Testing",
    description:
      "Advanced soil analysis to determine nutrients, pH level, and recommendations for best crop performance.",
    image: soilTestingImg,
  },
  {
    title: "Fertilizer Supply",
    description:
      "Wide range of organic and chemical fertilizers delivered directly to your farm at affordable prices.",
    image: fertilizersImg,
  },
  {
    title: "Farm Equipment Rental",
    description:
      "Modern tractors, harvesters, and other farm tools available for rent at competitive daily rates.",
    image: farmEquipmentImg,
  },
];

function ServicesSection() {
  return (
    <div className="services-section py-5">
      <Container>
        <h2 className="text-center mb-5 section-title">Our Featured Services</h2>
        <Row>
          {services.map((service, idx) => (
            <Col md={6} lg={3} key={idx} className="mb-4">
              <Card className="service-card shadow-sm h-100">
                <div className="service-img-wrapper">
                  <Card.Img variant="top" src={service.image} alt={service.title} />
                </div>
                <Card.Body>
                  <Card.Title className="service-title">{service.title}</Card.Title>
                  <Card.Text className="service-desc">{service.description}</Card.Text>
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
