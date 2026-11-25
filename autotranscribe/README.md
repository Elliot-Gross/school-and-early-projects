# AutoTranscribe

A Python/Tkinter desktop app that transcribes audio with Whisper and uses OpenAI's API to summarize the transcript. It saves the result as Markdown. Preview the [sample notes](Examples/What%20is%20Physics%20Notes.md) from this [physics explainer](https://www.youtube.com/watch?v=yWMKYID5fr8).

Run from this folder so the app can find its prompt files. Install Python 3 with Tkinter, then install the original SDK and Python dependencies:

```sh
python -m pip install "openai<1" pydub python-dotenv
python main.py
```

Set your own `OPENAI_API_KEY` before starting the app. The snapshot uses legacy API calls; installing the older SDK does not guarantee the original models are still available.

Snapshot of [Elliot-Gross/High-School-Projects](https://github.com/Elliot-Gross/High-School-Projects) at commit [`cdc4865`](https://github.com/Elliot-Gross/High-School-Projects/commit/cdc4865c9c20ee7a0234a86624f67a84776b0e2c), 2023-05-29.
