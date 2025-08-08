// src/pages/BookingsPage.jsx
import React, { useEffect, useState } from "react";
import { Container, Card, Table, Spinner, Badge } from "react-bootstrap";
import { CheckCircleFill, ClockFill, XCircleFill } from "react-bootstrap-icons";
import "../styles/BookingsPage.css";

function BookingsPage() {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/bookings")
      .then((res) => res.json())
      .then((data) => {
        setBookings(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setLoading(false);
      });
  }, []);

  const renderStatusBadge = (status) => {
    switch (status) {
      case "CONFIRMED":
        return <Badge bg="success" className="status-badge"><CheckCircleFill className="me-1"/> Confirmed</Badge>;
      case "PENDING":
        return <Badge bg="warning" text="dark" className="status-badge"><ClockFill className="me-1"/> Pending</Badge>;
      case "CANCELLED":
        return <Badge bg="danger" className="status-badge"><XCircleFill className="me-1"/> Cancelled</Badge>;
      default:
        return <Badge bg="secondary" className="status-badge">{status}</Badge>;
    }
  };

  return (
    <Container className="py-5">
      <Card className="shadow bookings-card">
        <Card.Body>
          <h2 className="text-center mb-4 text-success">Your Bookings</h2>
          {loading ? (
            <div className="text-center">
              <Spinner animation="border" />
            </div>
          ) : bookings.length === 0 ? (
            <p className="text-center no-bookings">No bookings yet.</p>
          ) : (
            <Table hover responsive className="bookings-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Service</th>
                  <th>Status</th>
                  <th>Booking Time</th>
                </tr>
              </thead>
              <tbody>
                {bookings.map((booking) => (
                  <tr key={booking.id} className="fade-in">
                    <td>{booking.id}</td>
                    <td>{booking.service.name}</td>
                    <td>{renderStatusBadge(booking.status)}</td>
                    <td>{new Date(booking.bookingTime).toLocaleString()}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          )}
        </Card.Body>
      </Card>
    </Container>
  );
}

export default BookingsPage;