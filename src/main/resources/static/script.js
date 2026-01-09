function createPaste() {
  const content = document.getElementById("content").value;
  const expiry = document.getElementById("expiry").value;

  fetch("http://localhost:8080/paste", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      content: content,
      expiryInMinutes: expiry || null
    })
  })
  .then(res => res.json())
  .then(data => {
    document.getElementById("result").innerText =
      "Snippet URL: http://localhost:8080/paste/" + data.shortCode;
  });
}
