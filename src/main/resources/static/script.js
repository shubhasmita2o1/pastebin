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
    const url = "http://localhost:8080/paste/" + data.shortCode;

    const detectedType = detectContentType(content);

    document.getElementById("snippetUrl").innerText =
      "Snippet URL: " + url + " | Type: " + detectedType;

    document.getElementById("result").style.display = "flex";
    document.getElementById("copyBtn").innerText = "Copy";
  });
}

function copyLink() {
  const text = document
    .getElementById("snippetUrl")
    .innerText
    .replace("Snippet URL: ", "")
    .split(" | ")[0];

  navigator.clipboard.writeText(text);

  document.getElementById("copyBtn").innerText = "Copied!";
}

/* ===============================
   AUTO DETECTION LOGIC
   Priority:
   1️⃣ Code (ALL languages)
   2️⃣ URL
   3️⃣ Text
   =============================== */
function detectContentType(text) {
  const content = text.trim();
  if (!content) return "TEXT";

  /* 1️⃣ CODE DETECTION */
  const codeResult = hljs.highlightAuto(content);
  if (codeResult.language && codeResult.relevance > 5) {
    return codeResult.language.toUpperCase();
  }

  /* 2️⃣ URL DETECTION */
  const urlRegex = /^(https?:\/\/[^\s]+)$/i;
  if (urlRegex.test(content)) {
    if (content.includes("youtube.com") || content.includes("youtu.be"))
      return "YOUTUBE URL";
    if (content.includes("google.com/maps"))
      return "GOOGLE MAPS URL";
    return "URL";
  }

  /* 3️⃣ FALLBACK */
  return "TEXT";
}
