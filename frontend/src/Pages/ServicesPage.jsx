import React, { useEffect, useState } from "react";
import {
  Container,
  Row,
  Col,
  Card,
  Button,
  Spinner,
  Modal,
  Form,
} from "react-bootstrap";
import { getServices, createBooking } from "../api/axios";
import "../styles/ServicesPage.css";

function ServicesPage() {
  const [services, setServices] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [selectedService, setSelectedService] = useState(null);
  const [bookingData, setBookingData] = useState({ date: "", notes: "" });

  useEffect(() => {
    getServices()
      .then((res) => {
        setServices(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setLoading(false);
      });
  }, []);

  const handleBookNow = (service) => {
    setSelectedService(service);
    setShowModal(true);
  };

  const handleBookingSubmit = async (e) => {
    e.preventDefault();
    try {
      await createBooking({
        serviceId: selectedService.id,
        date: bookingData.date,
        notes: bookingData.notes,
        userId: 1, // TODO: Replace with logged-in user ID
      });
      alert("Booking successful!");
      setShowModal(false);
    } catch (error) {
      console.error(error);
      alert("Booking failed!");
    }
  };

  return (
    <div className="services-page py-5">
      <Container>
        <h2 className="text-center mb-5 section-title">
          Explore Our Professional Services
        </h2>
        {loading ? (
          <div className="text-center">
            <Spinner animation="border" />
          </div>
        ) : (
          <Row>
            {services.map((service) => (
              <Col md={4} key={service.id} className="mb-4">
                <Card className="service-card shadow-sm h-100">
                  <Card.Body>
                    <Card.Title className="fw-bold">{service.name}</Card.Title>
                    <Card.Text>{service.description}</Card.Text>
                    <Card.Text>
                      <strong>Price:</strong> ₹{service.price}
                    </Card.Text>
                    <Button
                      variant="success"
                      className="w-100"
                      onClick={() => handleBookNow(service)}
                    >
                      Book Now
                    </Button>
                  </Card.Body>
                </Card>
              </Col>
            ))}
          </Row>
        )}
      </Container>

      {/* Booking Modal */}
      <Modal
        show={showModal}
        onHide={() => setShowModal(false)}
        centered
        className="booking-modal"
      >
        <Modal.Header closeButton>
          <Modal.Title>
            Book <span className="text-success">{selectedService?.name}</span>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleBookingSubmit}>
            <Form.Group className="mb-3">
              <Form.Label>Date</Form.Label>
              <Form.Control
                type="date"
                value={bookingData.date}
                onChange={(e) =>
                  setBookingData({ ...bookingData, date: e.target.value })
                }
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Notes</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                value={bookingData.notes}
                onChange={(e) =>
                  setBookingData({ ...bookingData, notes: e.target.value })
                }
              />
            </Form.Group>
            <div className="text-end">
              <Button variant="secondary" onClick={() => setShowModal(false)}>
                Cancel
              </Button>{" "}
              <Button variant="success" type="submit">
                Confirm Booking
              </Button>
            </div>
          </Form>
        </Modal.Body>
      </Modal>
    </div>
  );
}

export default ServicesPage;
