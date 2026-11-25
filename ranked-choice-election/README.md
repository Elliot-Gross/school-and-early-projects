# Ranked-Choice Election

A Python experiment in counting ranked ballots for a multi-winner election. It uses pandas and NumPy to clean the ballot data, transfer votes, and run counting rounds.

The original counter needs pandas 2.x; pandas 3.x rejects the fractional vote weights it assigns to integer columns. From this folder, install the dependencies and run the bundled sample:

```sh
python -m pip install "pandas>=2,<3" numpy
PYTHONPATH=sample python -c "from Election_Project import main; print(main('tests/test_data.csv', ['A', 'B', 'C'], 2))"
```

The sample selects `['A', 'C']`.

Snapshot of [Elliot-Gross/Election-System-Project](https://github.com/Elliot-Gross/Election-System-Project) at commit [`8b718a7`](https://github.com/Elliot-Gross/Election-System-Project/commit/8b718a76818e67329875cb37bdcd5230819f6c74), 2020-08-30.
