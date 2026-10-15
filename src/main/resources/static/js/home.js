function copyUrl() {

    const input = document.getElementById("shortUrl");

    navigator.clipboard.writeText(input.value)
        .then(() => {
            alert("Short URL copied!");
        })
        .catch(() => {
            alert("Unable to copy URL");
        });
}
const apiUrl = "/api";
async function shortenUrl(event) {

    event.preventDefault();
	document.getElementById("resultCard").style.display = "none";
    const url = document.getElementById("longUrl").value;

    const csrfToken = document
        .querySelector('meta[name="_csrf"]')
        .getAttribute("content");

    const csrfHeader = document
        .querySelector('meta[name="_csrf_header"]')
        .getAttribute("content");

    try {
        const response = await fetch(apiUrl, {
            method: "POST",

            headers: {
                "Content-Type": "application/json",
                [csrfHeader]: csrfToken
            },

            body: JSON.stringify({
                url: url
            })
        });

        const data = await response.json();
		console.log(data);
        const shortUrlInput = document.getElementById("shortUrl");

        shortUrlInput.value = "localhost:8080" + apiUrl + "/" + data.shortUrl;

        document.getElementById("resultCard").style.display = "block";

    } catch (error) {
        console.error("ERROR:", error);
    }
}

async function getRedirectionUrl(event) {
    event.preventDefault();

    try {
        // GET requests don't need a body, Content-Type, or a CSRF token
        const response = await fetch(apiUrl + "/123", {
            method: "GET"
        });

        // Check if the HTTP status is successful (200-299)
        if (!response.ok) {
            throw new Error(`Failed to fetch URL details (Status: ${response.status})`);
        }

        const data = await response.json();
        console.log(data);

        const shortUrlInput = document.getElementById("shortUrl");
        shortUrlInput.value = data.shortUrl;

        document.getElementById("resultCard").style.display = "block";

    } catch (error) {
        console.error("ERROR:", error);
        alert("Something went wrong while fetching the URL.");
    }
}