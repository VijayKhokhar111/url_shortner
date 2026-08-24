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

        const response = await fetch("/shortenUrl", {
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

        shortUrlInput.value = data.shortUrl;

        document.getElementById("resultCard").style.display = "block";

    } catch (error) {

        console.error("ERROR:", error);

    }
}
