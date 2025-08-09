import React, { useEffect, useState } from "react";
import { Container, Card, Table, Spinner, Badge, Alert } from "react-bootstrap";
import { CheckCircleFill, ClockFill, XCircleFill } from "react-bootstrap-icons";
import API from "../api/axios"; // Use your axios instance with interceptor
import "../styles/BookingsPage.css";

function BookingsPage() {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    API.get("/bookings")
      .then((res) => {
        setBookings(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Failed to load bookings:", err);
        setError("Failed to load bookings. Please try again.");
        setLoading(false);
      });
  }, []);

  const renderStatusBadge = (status) => {
    switch (status) {
      case "CONFIRMED":
        return (
          <Badge bg="success" className="status-badge">
            <CheckCircleFill className="me-1" /> Confirmed
          </Badge>
        );
      case "PENDING":
        return (
          <Badge bg="warning" text="dark" className="status-badge">
            <ClockFill className="me-1" /> Pending
          </Badge>
        );
      case "CANCELLED":
        return (
          <Badge bg="danger" className="status-badge">
            <XCircleFill className="me-1" /> Cancelled
          </Badge>
        );
        
      default:
        return (
          <Badge bg="secondary" className="status-badge">
            {status}
          </Badge>
        );
    }
  };

  if (loading)
    return (
      <div className="text-center mt-5">
        <Spinner animation="border" />
      </div>
    );

  if (error)
    return (
      <Container className="py-5">
        <Alert variant="danger">{error}</Alert>
      </Container>
    );

  if (bookings.length === 0)
    return (
      <Container className="py-5">
        <p className="text-center no-bookings">No bookings yet.</p>
      </Container>
    );

  return (
    <Container className="py-5">
      <Card className="shadow bookings-card">
        <Card.Body>
          <h2 className="text-center mb-4 text-success">Your Bookings</h2>
          <Table hover responsive className="bookings-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Service</th>
                <th>Status</th>
                <th>Preferred Date</th>
                <th>Booking Time</th>
                <th>Notes</th>
              </tr>
            </thead>
            <tbody>
              {bookings.map((booking) => (
                <tr key={booking.id} className="fade-in">
                  <td>{booking.id}</td>
                  <td>{booking.serviceName}</td>
                  <td>{renderStatusBadge(booking.status)}</td>
                  <td>
                    {booking.preferredDate
                      ? new Date(booking.preferredDate).toLocaleString()
                      : "-"}
                  </td>
                  <td>
                    {booking.bookingTime
                      ? new Date(booking.bookingTime).toLocaleString()
                      : "-"}
                  </td>
                  <td>{booking.notes || "-"}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Card.Body>
      </Card>
    </Container>
  );
}

export default BookingsPage;
