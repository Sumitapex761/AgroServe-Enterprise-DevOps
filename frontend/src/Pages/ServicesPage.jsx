import React, { useEffect, useState } from "react";
import { Container, Row, Col, Card, Button, Spinner } from "react-bootstrap";
import { getServices } from "../api/axios"; // <-- import function

function ServicesPage() {
  const [services, setServices] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getServices()
      .then((res) => {
        setServices(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching services:", err);
        setLoading(false);
      });
  }, []);

  return (
    <Container className="py-5">
      <h2 className="text-center mb-4">Our Services</h2>
      {loading ? (
        <div className="text-center">
          <Spinner animation="border" />
        </div>
      ) : (
        <Row>
          {services.map((service) => (
            <Col md={4} key={service.id} className="mb-4">
              <Card>
                <Card.Body>
                  <Card.Title>{service.name}</Card.Title>
                  <Card.Text>{service.description}</Card.Text>
                  <Card.Text>
                    <strong>Price:</strong> ₹{service.price}
                  </Card.Text>
                  <Button variant="success">Book Now</Button>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      )}
    </Container>
  );
}

export default ServicesPage;
